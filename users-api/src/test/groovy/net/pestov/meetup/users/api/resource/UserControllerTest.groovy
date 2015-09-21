package net.pestov.meetup.users.api.resource

import guru.nidi.ramltester.RamlDefinition
import guru.nidi.ramltester.RamlLoaders
import guru.nidi.ramltester.SimpleReportAggregator
import net.pestov.meetup.users.api.Application
import org.junit.Assert
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import static guru.nidi.ramltester.junit.RamlMatchers.validates
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * Created by eugene on 20/09/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = [ Application.class ])
@DirtiesContext(classMode = AFTER_CLASS)
class UserControllerTest {

    private static RamlDefinition api = RamlLoaders.fromFile('./../docs/api')
            .load('meetup-users.raml')
            .assumingBaseUri('http://localhost:8080')

    private static SimpleReportAggregator aggregator = new SimpleReportAggregator()

//    @ClassRule
//    public static ExpectedUsage expectedUsage = new ExpectedUsage(aggregator)

    @Autowired
    private WebApplicationContext wac

    private MockMvc mockMvc

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    @Test
    public void validSpecification() throws Exception {
        Assert.assertThat(api.validate(), validates())
    }

    @Test
    public void getUsersAll() throws Exception {
        mockMvc.perform(get('/users')
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(api.matches().aggregating(aggregator))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.links').isArray())
                .andExpect(jsonPath('$.content').isArray())
                .andExpect(jsonPath('$.page').exists())
    }

    @Test
    public void getUserConnectiosAll() throws Exception {
        mockMvc.perform(get('/users/{userName}/connections', 'eupestov')
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(api.matches().aggregating(aggregator))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.links').isArray())
                .andExpect(jsonPath('$.content').isArray())
                .andExpect(jsonPath('$.page').exists())
    }
}

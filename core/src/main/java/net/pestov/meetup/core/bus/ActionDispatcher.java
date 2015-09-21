package net.pestov.meetup.core.bus;

/**
 * Created by eugene on 20/09/2015.
 */
public interface ActionDispatcher {
    <T extends Action> void dispatch(T action);
}

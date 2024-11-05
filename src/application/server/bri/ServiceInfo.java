package application.server.bri;

import application.server.ServiceState;

public class ServiceInfo {
    private Class<? extends BriService> serviceClass;
    private ServiceState state;

    public ServiceInfo(Class<? extends BriService> serviceClass) {
        this.serviceClass = serviceClass;
        this.state = ServiceState.INACTIVE;
    }

    public Class<? extends BriService> getServiceClass() {
        return serviceClass;
    }

    public ServiceState getState() {
        return state;
    }

    public void setServiceClass(Class<? extends BriService> serviceClass) {
        this.serviceClass = serviceClass;
    }

    public void activate() {
        state = ServiceState.ACTIVE;
    }

    public void deactivate() {
        state = ServiceState.INACTIVE;
    }
}

package hotelsearchengine.models;

public class Service {

    int serviceId;
    String serviceName;

    public Service(int serviceId, String serviceName) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }
}


package resources;

//enum is the special class in java which has the collection of constants and method
public enum APIResources {
    AddPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json"),
    updatePlaceAPI("/maps/api/place/update/json");
    private String resources;

    APIResources(String resources) {
        this.resources = resources;
    }

    public String getResources() {

        return resources;
    }
}


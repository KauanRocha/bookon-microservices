package br.com.bookon.server.payload.response.postgres;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "searchresults")
@XmlType(propOrder = { "place", "timestamp", "attribution", "querystring", "more_url", "exclude_place_ids" })
public class NominatimGeolocationResponse {
    private String timestamp;
    private String attribution;
    private String querystring;
    private String more_url;
    private String exclude_place_ids;
    private Place place;

    @XmlElement
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @XmlElement
    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    @XmlElement
    public String getQuerystring() {
        return querystring;
    }

    public void setQuerystring(String querystring) {
        this.querystring = querystring;
    }

    @XmlElement
    public String getMore_url() {
        return more_url;
    }

    public void setMore_url(String more_url) {
        this.more_url = more_url;
    }

    @XmlElement(name = "exclude_place_ids")
    public String getExclude_place_ids() {
        return exclude_place_ids;
    }

    public void setExclude_place_ids(String exclude_place_ids) {
        this.exclude_place_ids = exclude_place_ids;
    }

    @XmlElement
    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "SearchResults [timestamp=" + timestamp + ", attribution=" + attribution + ", querystring=" + querystring
                + ", more_url=" + more_url + ", exclude_place_ids=" + exclude_place_ids + ", place=" + place + "]";
    }

@XmlType(propOrder = { "place_id", "osm_type", "osm_id", "lat", "lon", "boundingbox", "place_rank", "address_rank",
        "display_name", "class", "type", "importance", "geokml", "house_number", "road", "hamlet", "town", "village",
        "city", "ISO3166-2-lvl8", "state_district", "state", "ISO3166-2-lvl4", "postcode", "country", "country_code" })
public class Place {
    private String place_id;
    private String osm_type;
    private String osm_id;
    private String lat;
    private String lon;
    private String boundingbox;
    private String place_rank;
    private String address_rank;
    private String display_name;
    private String classValue;
    private String type;
    private String importance;
    private String geokml;
    private String house_number;
    private String road;
    private String hamlet;
    private String town;
    private String village;
    private String city;
    private String ISO3166_2_lvl8;
    private String state_district;
    private String state;
    private String ISO3166_2_lvl4;
    private String postcode;
    private String country;
    private String country_code;

    @XmlElement(name = "place_id")
    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    @XmlElement(name = "osm_type")
    public String getOsm_type() {
        return osm_type;
    }

    public void setOsm_type(String osm_type) {
        this.osm_type = osm_type;
    }

    @XmlElement(name = "osm_id")
    public String getOsm_id() {
        return osm_id;
    }

    public void setOsm_id(String osm_id) {
        this.osm_id = osm_id;
    }

    @XmlElement
    public Double getLatitude() {
        return Double.parseDouble(lat);
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @XmlElement
    public Double getLongitude() {
        return Double.parseDouble(lon);
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @XmlElement
    public String getBoundingbox() {
        return boundingbox;
    }

    public void setBoundingbox(String boundingbox) {
        this.boundingbox = boundingbox;
    }

    @XmlElement(name = "place_rank")
    public String getPlace_rank() {
        return place_rank;
    }

    public void setPlace_rank(String place_rank) {
        this.place_rank = place_rank;
    }

    @XmlElement(name = "address_rank")
    public String getAddress_rank() {
        return address_rank;
    }

    public void setAddress_rank(String address_rank) {
        this.address_rank = address_rank;
    }

    @XmlElement(name = "display_name")
    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    @XmlElement(name = "class")
    public String getClassValue() {
        return classValue;
    }

    public void setClassValue(String classValue) {
        this.classValue = classValue;
    }

    @XmlElement
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement
    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    @XmlElement
    public String getGeokml() {
        return geokml;
    }

    public void setGeokml(String geokml) {
        this.geokml = geokml;
    }

    @XmlElement(name = "house_number")
    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    @XmlElement
    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    @XmlElement
    public String getHamlet() {
        return hamlet;
    }

    public void setHamlet(String hamlet) {
        this.hamlet = hamlet;
    }

    @XmlElement
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @XmlElement
    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    @XmlElement
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlElement(name = "ISO3166-2-lvl8")
    public String getISO3166_2_lvl8() {
        return ISO3166_2_lvl8;
    }

    public void setISO3166_2_lvl8(String ISO3166_2_lvl8) {
        this.ISO3166_2_lvl8 = ISO3166_2_lvl8;
    }

    @XmlElement(name = "state_district")
    public String getState_district() {
        return state_district;
    }

    public void setState_district(String state_district) {
        this.state_district = state_district;
    }

    @XmlElement
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @XmlElement(name = "ISO3166-2-lvl4")
    public String getISO3166_2_lvl4() {
        return ISO3166_2_lvl4;
    }

    public void setISO3166_2_lvl4(String ISO3166_2_lvl4) {
        this.ISO3166_2_lvl4 = ISO3166_2_lvl4;
    }

    @XmlElement
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @XmlElement
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlElement(name = "country_code")
    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    @Override
    public String toString() {
        return "Place [place_id=" + place_id + ", osm_type=" + osm_type + ", osm_id=" + osm_id + ", lat=" + lat
                + ", lon=" + lon + ", boundingbox=" + boundingbox + ", place_rank=" + place_rank + ", address_rank="
                + address_rank + ", display_name=" + display_name + ", classValue=" + classValue + ", type=" + type
                + ", importance=" + importance + ", geokml=" + geokml + ", house_number=" + house_number + ", road="
                + road + ", hamlet=" + hamlet + ", town=" + town + ", village=" + village + ", city=" + city
                + ", ISO3166_2_lvl8=" + ISO3166_2_lvl8 + ", state_district=" + state_district + ", state=" + state
                + ", ISO3166_2_lvl4=" + ISO3166_2_lvl4 + ", postcode=" + postcode + ", country=" + country
                + ", country_code=" + country_code + "]";
    }
}
}
package br.com.bookon.server.payload.response.postgres;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "reversegeocode")
public class NominatimAdressResponse {


    private String timestamp;
    private String attribution;
    private String querystring;
    private Result result;
    private AddressParts addressparts;

    public String getTimestamp() {
		return timestamp;
	}

	public String getAttribution() {
		return attribution;
	}

	public String getQuerystring() {
		return querystring;
	}

	public Result getResult() {
		return result;
	}

	@XmlElement(name = "timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @XmlElement(name = "attribution")
    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    @XmlElement(name = "querystring")
    public void setQuerystring(String querystring) {
        this.querystring = querystring;
    }

    @XmlElement(name = "result")
    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ReverseGeocodeResponse{" +
                "timestamp='" + timestamp + '\'' +
                ", attribution='" + attribution + '\'' +
                ", querystring='" + querystring + '\'' +
                ", result=" + result +
                '}';
    }
    
    public AddressParts getAddressparts() {
		return addressparts;
	}


	@XmlElement(name = "addressparts")
    public void setAddressparts(AddressParts addressparts) {
        this.addressparts = addressparts;
    }

    public static class Result {

        private String place_id;
        private String osm_type;
        private String osm_id;
        private String ref;
        private String lat;
        private String lon;
        private String boundingbox;
        private int place_rank;
        private int address_rank;

        public String getPlace_id() {
			return place_id;
		}


		public void setPlace_id(String place_id) {
			this.place_id = place_id;
		}


		public String getOsm_type() {
			return osm_type;
		}


		public void setOsm_type(String osm_type) {
			this.osm_type = osm_type;
		}


		public String getOsm_id() {
			return osm_id;
		}


		public void setOsm_id(String osm_id) {
			this.osm_id = osm_id;
		}


		public String getRef() {
			return ref;
		}


		public void setRef(String ref) {
			this.ref = ref;
		}


		public String getLat() {
			return lat;
		}


		public void setLat(String lat) {
			this.lat = lat;
		}


		public String getLon() {
			return lon;
		}


		public void setLon(String lon) {
			this.lon = lon;
		}


		public String getBoundingbox() {
			return boundingbox;
		}


		public void setBoundingbox(String boundingbox) {
			this.boundingbox = boundingbox;
		}


		public int getPlace_rank() {
			return place_rank;
		}


		public void setPlace_rank(int place_rank) {
			this.place_rank = place_rank;
		}


		public int getAddress_rank() {
			return address_rank;
		}


		public void setAddress_rank(int address_rank) {
			this.address_rank = address_rank;
		}


		
    }

    public static class AddressParts {

        private String road;
        private String city;
        private String town;
        private String county;
        private String state;
        private String ISO3166_2_lvl4;
        private String country;
        private String country_code;
        
		public String getRoad() {
			return road;
		}
		public void setRoad(String road) {
			this.road = road;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getCounty() {
			return county;
		}
		public void setCounty(String county) {
			this.county = county;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getISO3166_2_lvl4() {
			return ISO3166_2_lvl4;
		}
		public void setISO3166_2_lvl4(String iSO3166_2_lvl4) {
			ISO3166_2_lvl4 = iSO3166_2_lvl4;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getCountry_code() {
			return country_code;
		}
		public void setCountry_code(String country_code) {
			this.country_code = country_code;
		}
		public String getTown() {
			return town;
		}
		public void setTown(String town) {
			this.town = town;
		}

    }
    
}

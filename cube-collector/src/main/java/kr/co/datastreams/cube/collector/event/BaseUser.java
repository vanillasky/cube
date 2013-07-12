package kr.co.datastreams.cube.collector.event;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 11
 * Time: 오전 11:26
 * To change this template use File | Settings | File Templates.
 */
public class BaseUser implements User {
    private String name;
    private String location;
    private String lang;
    private String description;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String descrption) {
        this.description = descrption;
    }
}

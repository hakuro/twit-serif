// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package jp.dip.hakuro.twitserif.domain;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jp.dip.hakuro.twitserif.domain.Serifs;

privileged aspect Serifs_Roo_Json {
    
    public String Serifs.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static Serifs Serifs.fromJsonToSerifs(String json) {
        return new JSONDeserializer<Serifs>().use(null, Serifs.class).deserialize(json);
    }
    
    public static String Serifs.toJsonArray(Collection<Serifs> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<Serifs> Serifs.fromJsonArrayToSerifses(String json) {
        return new JSONDeserializer<List<Serifs>>().use(null, ArrayList.class).use("values", Serifs.class).deserialize(json);
    }
    
}
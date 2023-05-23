package com.apiperformancetests.api.model.mongo;

    import com.apiperformancetests.api.model.mysql.MysqlClass;
    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.mapping.Document;
    import com.fasterxml.jackson.annotation.JsonIgnore;
    import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection="student")
public class MongoStudent {
    @Id
    private String id;
    private MongoClass classId;
    private String firstName;
    private String lastName;
    private String situation;

    public MongoStudent(MongoClass classId, String firstName, String lastName, String situation) {
        this.classId = classId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.situation = situation;
    }

    public MongoStudent() {
    }

    public String getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setClassId(MongoClass classId) {
        this.classId = classId;
    }

    public MongoClass getClassId() {
        return this.classId;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getSituation() {
        return this.situation;
    }

}

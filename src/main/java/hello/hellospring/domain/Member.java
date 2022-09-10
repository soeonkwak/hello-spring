package hello.hellospring.domain;

public class Member {

    private long id; //임의의 값. sequence든 시스템에 의해서 저장되는 값.
    private String name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

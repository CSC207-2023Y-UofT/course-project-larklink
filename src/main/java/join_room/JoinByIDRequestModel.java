package join_room;

public class JoinByIDRequestModel {
    private final String name;

    public JoinByIDRequestModel(String name){
        this.name = name;
    }

    String getName(){
        return name;
    }

}

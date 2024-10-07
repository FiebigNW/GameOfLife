public class Cell{

    public boolean alive;


    public Cell(boolean alive){
        this.alive = alive;
    }

    public boolean getAlive(){
        return alive;
    }

    public void setAlive(boolean life){
        alive = life;
    }

    public String toString(){
        if(alive){
            return "O";
        } else{
            return ".";
        }
    }
}

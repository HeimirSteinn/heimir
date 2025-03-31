package vinnsla;

public class Spilastokkur {
    private String[] sortir = {"Hjarta", "Spaði", "Tígull", "Lauf"};
    public Spilastokkur(){

    }
    public String getSort(){
        int rndm = (int) (Math.random() * 4) + 1;
        switch (rndm) {
            case 1 -> {
                return "hjarta";
            }
            case 2 -> {
                return "spadi";
            }
            case 3 -> {
                return "tigull";
            }
            case 4 -> {
                return "lauf";
            }
            default -> {
                return "";
            }
        }
    }
}

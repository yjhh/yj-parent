package yj.aspectj;

public aspect TargetProxy {

    public static void main(String[] args) {
        System.out.println("main run");
        add(1);
    }

    public static void add(int i){
        System.out.println("add"+i);
        throw new RuntimeException("error");
    }

}

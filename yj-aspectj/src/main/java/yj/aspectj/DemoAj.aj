package yj.aspectj;

public aspect DemoAj {

    pointcut targerCut(int x) : execution(* add(int)) && args(x);
    //流程控制，会拦截每一行的字节码对象，由于aspectj是静态织入，需要把自己排除，否则会拦截自身形成死循环
//    pointcut cflowMain() : cflow(targerCut()) && !within(DemoAj);

//    before() : targerCut(){
//        System.out.println(thisJoinPoint.getTarget());
//        System.out.println(thisJoinPoint.getThis());
//        System.out.println(thisJoinPoint.getSourceLocation());
//        System.out.println("before");
//    }
//
//    after(): targerCut(){
//        System.out.println("after");
//    }

//    before():cflowMain(){
//        System.out.println("cfow");
//    }
   void around(int x):targerCut(x){
        x = x+100;
        proceed(x);
    }

    after(int x) throwing (Exception e) : targerCut(x){
//        e.printStackTrace();
    }
}

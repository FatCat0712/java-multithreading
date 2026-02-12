package concurrency.volatileex;

public class Exchanger2 {
    private int val1 = 0;
    private int val2 = 0;
    private volatile int val3 = 0;

    public void setValues(Values source) {
        this.val1 = source.getVal1();
        this.val2 = source.getVal2();
        this.val3 = source.getVal3();
    }

    public void getValues(Values dest) {
        dest.setVal3(this.val3);
        dest.setVal1(this.val1);
        dest.setVal2(this.val2);
    }
}

package concurrency.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Calculator {
    public static class Calculation {
        public static final int UNSPECIFIED = -1;
        public static final int ADDITION = 0;
        public static final int SUBSTRACTION = 1;
        int type = UNSPECIFIED;

        public double value;

        public Calculation(int type, double value) {
            this.type = type;
            this.value = value;
        }
    }

    private double result = 0.0D;

    Lock lock = new ReentrantLock();

    public void add(double value) {
        try {
            lock.lock();
            this.result += value;
        }finally {
            lock.unlock();
        }
    }

    public void subtract(double value) {
        try {
            lock.lock();
            this.result -= value;
        }
        finally {
            lock.unlock();
        }
    }

    public void calculate(Calculation ...calculations) {
        try {
            lock.lock();
            for(Calculation calculation : calculations) {
                switch (calculation.type) {
                    case Calculation.ADDITION -> add(calculation.value);
                    case Calculation.SUBSTRACTION -> subtract(calculation.value);
                }
            }
        }
        finally {
            lock.unlock();
        }
    }

}

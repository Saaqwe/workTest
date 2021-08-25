package com.company;

public class DotPair {

    public String dotsStr;
    public int x;
    public int y;


    public boolean inFirstGroup;

    public boolean inSecondGroup;

    public boolean inThirdGroup;

    public DotPair(int x, int y) {
        this.x = x;
        this.y = y;
        //Вычислем вхождение в группы
        if(y >= x) {
            this.inFirstGroup = true;
        }
        if(y >= x*x) {
            this.inSecondGroup = true;
        }
        if(y >= x*x*x) {
            this.inThirdGroup = true;
        }
    }
    @Override
    public String toString() {
        return "" + x + " " + y;
    }
}

#include<iostream>

void f1(int& x){
    x = -1;
}

int main(){
    int x = 100;
    f1(x);
    printf("x:%d", x);
    return 0;
}
#include <iostream>

using namespace std;

int main()
{
    int a = 0xFFFFFFFF;
    cout << "echo";
    std::cout << a << std::endl;
    std::cout << (int)a << std::endl;
    
    cout << hex << a << endl; 
    std::cout << hex << (a >> 1) << std::endl;
    std::cout << hex << ((unsigned)a >> 1) << std::endl;
    return 0;
}
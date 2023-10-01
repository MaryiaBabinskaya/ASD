#include <iostream>
#include <vector>
using namespace std;

void findAllN(int i, int L){
    vector <int> intervals;
    int prawy, lewy, mid, counter_przedzial = 0, counter_porownan;
    int last = -1; //ostatnie znalezione N

    for(int N = 1; N <= 10000; N++){
        prawy = 0;
        lewy = N - 1;
        counter_porownan = 0;

        while( prawy <= lewy && counter_porownan <= L ) {
            mid = (prawy + lewy) / 2;
            counter_porownan++;
            if (mid == i && counter_porownan == L) {
                if(N != last + 1) { //jezeli N nie jest kolejna wartoscia w przedziale
                    if (intervals.size() == 0) {
                        intervals.push_back(N);
                    }
                    else {
                        intervals.push_back(last);
                        intervals.push_back(N);
                        counter_przedzial++;
                    }
                }
                last = N;
                break;
            }
            if (mid >= i) lewy = mid - 1;
            else prawy = mid + 1;
        }
    }
    if(!intervals.empty()) {
        intervals.push_back(last); //ostatnie wstawienie
        counter_przedzial++;
    }
    cout << counter_przedzial << endl;
    int k = 0;
    while(k < intervals.size()){
        cout << intervals.at(k++) << " " << intervals.at(k++) <<endl;
    }
}
int main() {
    findAllN(10,3);
    return 0;
}
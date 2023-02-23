public class MyList<T> {

    private int capacity;
    T[] array;
    private int currIndex = 0;

    public MyList() { //Boş constructor kullanılırsa dizinin başlangıç boyutu 10 olmalıdır
        capacity = 10;
        array = (T[]) new Object[10];
    }

    public MyList(int size) { //Dizinin başlangıç değeri capacity parametresinden alınmalıdır
        capacity = size;
        array = (T[]) new Object[size];
    }

    public void add(T data) { //Sınıfa ait diziye eleman eklemek için kullanılmalıdır. Eğer dizide yeteri kadar yer yok ise, dizi boyutu 2 katına çıkartılmalıdır
        if (currIndex + 1 == array.length)
            resize(2 * array.length); //Dizinin eleman sayısı ihtiyaç duydukça 2 katı şeklinde artmalı

        array[currIndex] = data;
        currIndex++;
    }

    public T remove(int index) { //Verilen indisteki veriyi silmeli ve silinen indis sonrasındaki verileri sola doğru kaydırmalı. Geçersiz indis girilerse null döndürür
        if (index >= capacity) return null;

        T temp = array[index];

        for (int i = index; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        currIndex--;

        return temp;
    }

    public T set(int index, T data) { //Verilen indisteki veriyi yenisi ile değiştirme işlemini yapmalıdır. Geçersiz indis girilerse null döndürür
        if (index >= capacity) return null;

        array[index] = data;
        return data;
    }

    public T get(int index) { //Verilen indisteki değeri döndürür. Geçersiz indis girilerse null döndürür
        try {
            return array[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public int indexOf(T data) { //Parametrede verilen nesnenin listedeki indeksini verir. Nesne listede yoksa -1 değerini verir
        int temp = 0;
        boolean arrayHasTheData = false;

        for (T value : array) {
            if (value == data) {
                arrayHasTheData = true;
                break;
            }
            temp++;
        }

        if (!arrayHasTheData) return -1;
        else return temp;
    }

    public int lastIndexOf(T data) { //Belirtilen öğenin listedeki son indeksini söyler. Nesne listede yoksa -1 değerini verir
        int temp = array.length - 1;
        boolean arrayHasTheData = false;

        for (int i = array.length - 1; i >= 0; i--) { //Sonuncuyu bulmak için sondan başlıyor
            if (array[i] == data) {
                arrayHasTheData = true;
                break;
            }
            temp--;
        }

        if (!arrayHasTheData) return -1;
        else return temp;
    }

    private void resize(int capacity) {
        this.capacity = capacity;

        T[] copy = (T[]) new Object[capacity];
        for (int i = 0; i < array.length; i++)
            copy[i] = array[i];
        array = copy;
    }

    public boolean isEmpty() { //Listenin boş olup olmadığını söyler
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null)
                return false;
        }
        return true;
    }

    public boolean contains(T data) { //Parametrede verilen değerin dizide olup olmadığını söyler
        for (T value : array) {
            if (value == data) return true;
        }
        return false;
    }

    public MyList<T> subList(int start, int finish) { //Parametrede verilen indeks aralığına ait bir liste döner

        MyList<T> temp = new MyList<>();
        boolean startAdding = false;

        for (int i = 0; i < array.length; i++) {
            if (i == start) {
                startAdding = true;
            }

            if (i == finish) {
                startAdding = false;
            }

            if (startAdding) {
                temp.add(array[i]);
            }
        }
        return temp;

    }

    public T[] toArray() { //Listedeki öğeleri, aynı sırayla bir array haline getirir
        return array;
    }

    public void clear() { //Listedeki bütün öğeleri siler, boş liste haline getirir
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    public int size() { //dizideki eleman sayısını verir
        return currIndex;
    }

    public int getCapacity() { //dizinin kapasite değerini verir
        return capacity;
    }

    public String toString() {
        String temp = "[";

        for (T value : array) {
            if (value != null) {
                temp += value.toString() + ",";
            }
        }

        if (!(temp.charAt(temp.length() - 1) == '[')) //Listenin boş olma durumu
            temp = temp.substring(0, temp.length() - 1); //Sondaki fazladan virgül için

        return temp + "]";
    }
}

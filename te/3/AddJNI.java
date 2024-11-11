public class AddJNI {

    // Load the shared library
    static {
        System.loadLibrary("native");
    }

    // Declare native method
    private native int add(int a, int b);

    // Main method to test the native method
    public static void main(String[] args) {
        AddJNI obj = new AddJNI();
        int sum = obj.add(5, 3);
        System.out.println("Sum from native method: " + sum);
    }
}

//commands

// 1)after AddJNI.java file: 
// -->javac -h . AddJNI.java

// 2)after creating c file: otherlocation/computer/usr/lib/jvm/java-1.8.0-openjdk-amd64/include

// -->gcc -c -fPIC -I /usr/lib/jvm/java-1.8.0-openjdk-amd64/include -I /usr/lib/jvm/java-1.8.0-openjdk-amd64/include/linux AddJNI.c -o AddJNI.o
// -->gcc shared -fPIC -o libnative.so AddJNI.o -lc
// -->java -Djava.library.path=. AddJNI
#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_app3_MainActivity_string2(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Enter your name please.";
    return env->NewStringUTF(hello.c_str());
}


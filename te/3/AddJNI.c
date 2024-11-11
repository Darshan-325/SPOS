#include <jni.h>
#include "AddJNI.h"
#include <stdio.h>

// Implementation of the native method
JNIEXPORT jint JNICALL Java_AddJNI_add(JNIEnv *env, jobject thisobj, jint a, jint b) 
{
    jint res;
    res = a+b
    return jint;
}

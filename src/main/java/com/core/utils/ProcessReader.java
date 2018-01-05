package com.core.utils;

import java.io.InputStream;

public class ProcessReader implements Runnable {

public static final String STR_PROCESS_EXIST_ERROR_INFO="process hasn't exited";

private Process p;
public ProcessReader(Process aProcess) {
p = aProcess;
}

public void run() {
// TODO Auto-generated method stub
if(p == null) {
return;
}

//Check whether the process is over or not.
try {
p.exitValue();
} catch(IllegalStateException e) {
//System.err.println("The process has been already exited with the exitcode of:" + p.exitValue());
if(! e.getMessage().contains(STR_PROCESS_EXIST_ERROR_INFO)) {
System.out.println("Not reading the process response..");
return;
} else {
System.out.println("Known error has occurred. Ignoring it now");
}
}
System.out.println("Starts reading the process stream...");
try {
int c;
InputStream in = p.getInputStream();
while(((c = in.read()) != -1)) {
System.out.print((char)c);
}
} catch(Exception e) {
System.out.println("Error occurred while reading. Error:" + e.getMessage());
e.printStackTrace();
}
}
}
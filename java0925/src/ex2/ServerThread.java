package ex2;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author 오세현
 * Document : ServerThread 
 * Created on : 2014. 9. 25, 오후 1:47:57
 */
class ServerThread {

    //서버로부터 위임받은 소켓
    private Socket socket;
    //서버로부터 받은 서버의 주소
    private MultiSocketServer server;
    //서버로부터 연결된 두 스트림
    private PrintWriter pw;
    private BufferedReader in;
    //x,y좌표, 컬러값
    private int x;
    private int y;
    private String col;
    

    public ServerThread(Socket socket, MultiSocketServer server) {
        this.socket = socket;
        this.server = server;

        try {
            pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()), true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Thread를 내부클래스로 정의
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    //소켓 하나당 스트림이 연결
                    in = new BufferedReader(new InputStreamReader(ServerThread.this.socket.getInputStream()));
                    while(true){
                        //소켓으로부터 입력을 받기 위해 대기하는 메서드
                        String msg = in.readLine();
                        draw();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            private void draw() {
                //ServerThread.this.server.sendMsg(msg, ServerThread.this);
                //ServerThread.this.server.sendMsg(col, null);
            }
        });
        //Thread를 start해서 스케쥴러에게 일을 요청한다.
        t.start();
    }

    public PrintWriter getPw() {
        return pw;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getCol() {
        return col;
    }

}

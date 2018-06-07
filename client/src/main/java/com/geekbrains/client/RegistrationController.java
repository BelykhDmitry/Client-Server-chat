package com.geekbrains.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RegistrationController {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    @FXML
    TextField login, password, nickname;

    @FXML
    Label result;

    public void tryToRegister() {
        try {
            if (socket == null || socket.isClosed()) {
                socket = new Socket("localhost", 8189);
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("/registration " + login.getText() + " " + password.getText() + " " + nickname.getText());
                // /registration login1 pass1 nick1
                String answer = in.readUTF();
                result.setText(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

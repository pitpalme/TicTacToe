package de.fernuni_hagen.k01618;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Ein Exemplar dieser Klasse repräsentiert eine Kommunikationsschnittstelle zu einem Server, der
 * eine Anfrage in Form von Text engegennimmt, mit einer Nachricht in Form von Text antwortet und
 * dann die Verbindung beendet.
 *
 * @author Michael Paap
 */
public class Communicator {

    private String server;

    private int port;

    private String charsetName;

    /**
     * Erstellt ein Exemplar dieser Klasse für die Kommunikation mit dem übergebenen Server, Port
     * und Encoding.
     *
     * @param server
     *            Server, der benutzt werden soll, als auflösbarer Servername oder IP-Adresse
     * @param port
     *            Port, der benutzt werden soll. Port muss zwischen 0 und 65535 liegen.
     * @param charsetName
     *            der Name des zu verwendende Encodings, z.B. "UTF-8"
     */
    public Communicator(final String server, final int port,
            final String encoding) {
        this.server = server;
        this.port = port;
        charsetName = encoding;
    }

    /**
     * Sendet eine Nachricht an den Server und liefert die Antwort als String.
     *
     * @param message
     *            Nachricht die an den Server gesendet werden soll
     * @return die vom Server gelesene Antwort
     * @throws ComunicatorException
     *             wenn beim Aufbau der Verbindung, beim Lesen oder beim Schreiben eine Eception
     *             auftritt. Die ursächliche Exception wird mitgegeben und kann beim
     *             CommunicatorException-Exemplar mit getCause() abgefragt werden.
     */
    public String communicate(final String message) throws CommunicatorException {
        Socket socket = null;
        try {
            socket = connectToServer();
            sendRequestToServer(message, socket);
            return readResponseFromServer(socket);
        } finally {
            closeSocket(socket);
        }
    }

    private Socket connectToServer() throws CommunicatorException {
        try {
            return new Socket(server, port);
        } catch (IOException e) {
            throw new CommunicatorException(
                    "An exception occured while trying to connect to server. ", e);
        }
    }

    private void sendRequestToServer(final String message, final Socket socket) throws CommunicatorException {
        try {
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, charsetName);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(message);
            bw.flush();
        } catch (IOException e) {
            throw new CommunicatorException(
                    "An exception occured while trying to write to server.", e);
        }
    }

    private String readResponseFromServer(final Socket socket) throws CommunicatorException {
        try {
            InputStream in = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(in, charsetName);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            int c = -1;
            while ((c = br.read()) != -1) {
                sb.append((char)c);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new CommunicatorException(
                    "An exception occured while trying to read from server.", e);
        }
    }

    private void closeSocket(final Socket socket) {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ioe) {
            // Hier gibts dann auch nichts mehr zu tun
        }
    }
}
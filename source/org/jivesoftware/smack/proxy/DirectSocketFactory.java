package org.jivesoftware.smack.proxy;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.SocketFactory;
import org.jivesoftware.smack.util.DNSUtil;

/**
 * SocketFactory for direct connection
 * 
 * @author Atul Aggarwal
 */
class DirectSocketFactory 
    extends SocketFactory
{

    public DirectSocketFactory()
    {
    }

    static private int roundrobin = 0;

    public Socket createSocket(String host, int port) 
        throws IOException, UnknownHostException
    {
    	Socket newSocket = new Socket(Proxy.NO_PROXY);
        newSocket.setReuseAddress(true);
        InetAddress resolved = InetAddress.getByName(host);
        //InetAddress resolved[] = InetAddress.getAllByName(host);
        //newSocket.connect(new InetSocketAddress(resolved[(roundrobin++) % resolved.length],port));
        newSocket.connect(new InetSocketAddress(resolved, port));
        
        return newSocket;
    }

    public Socket createSocket(String host ,int port, InetAddress localHost,
                                int localPort)
        throws IOException, UnknownHostException
    {
        return new Socket(host,port,localHost,localPort);
    }

    public Socket createSocket(InetAddress host, int port)
        throws IOException
    {
        Socket newSocket = new Socket(Proxy.NO_PROXY);
        newSocket.setReuseAddress(true);
        newSocket.connect(new InetSocketAddress(host,port));
        return newSocket;
    }

    public Socket createSocket( InetAddress address, int port, 
                                InetAddress localAddress, int localPort) 
        throws IOException
    {
        return new Socket(address,port,localAddress,localPort);
    }

}

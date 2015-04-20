package com.example.xmltest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//loadConfig3();
		XmlParser xmlParser = new XmlParser("/com/example/xmltest/config.xml");
		xmlParser.load();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	
	}
	
	/*private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
	    List entries = new ArrayList();

	    parser.require(XmlPullParser.START_TAG, ns, "feed");
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        // Starts by looking for the entry tag
	        if (name.equals("entry")) {
	            entries.add(readEntry(parser));
	        } else {
	            skip(parser);
	        }
	    }  
	    return entries;
	}*/
	
	
	
	public void loadConfig3() {
		InputStream in = null;
		try {  
			in = MainActivity.class.getResourceAsStream("/com/example/xmltest/config3.xml");
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(in); 
			Element rootElement = doc.getDocumentElement();
			NodeList nodes = rootElement.getChildNodes();
			for(int i=0; i<nodes.getLength();i++){
				Node node = nodes.item(i);
				String name = node.getNodeName();
				String test = name ;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
		}
			
		
	}
	
	public void loadConfig2() {
		InputStream in = null;
		try{  
			in = MainActivity.class.getResourceAsStream("/com/example/xmltest/config2.xml");
			XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();  
			parser.setInput(in,null);  
			parser.next();  //goto next event
			parser.require(XmlPullParser.START_TAG, null, "statuses");  //test
			while (parser.nextTag() != XmlPullParser.END_TAG) {
				parser.require(XmlPullParser.START_TAG, null, "status");  
				for (int i=0;i<3;i++){  
					parser.nextTag();  
					Log.v("tag",parser.getName()+"="+ parser.nextText());  
				}  
				while (parser.nextTag() != XmlPullParser.END_TAG) {  
					parser.require(XmlPullParser.START_TAG, null, "user");  
					while (parser.nextTag() != XmlPullParser.END_TAG) {  
							String name = parser.getName();  
							String text2 = parser.nextText();  
							Log.v("tag","text2"+text2);  
							parser.require(XmlPullParser.END_TAG, null, name);  
					}  
					parser.require(XmlPullParser.END_TAG, null, "user");  
				}  
				parser.require(XmlPullParser.END_TAG, null, "status");  
			}  
			parser.require(XmlPullParser.END_TAG, null, "statuses");  
			parser.next();  
			parser.require(XmlPullParser.END_DOCUMENT, null, null);  
			// global.userinfo.dump();  
		} catch (XmlPullParserException e) { 
			e.printStackTrace();
		} catch (Exception e) { 
			e.printStackTrace();
		}  
	}
	
	
	public void loadConfig(){
		InputStream in = null;
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			in = MainActivity.class.getResourceAsStream("/com/example/xmltest/config.xml");
			parser.setInput(in, null);
			parser.nextTag();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        //return readFeed(parser);
	}

}

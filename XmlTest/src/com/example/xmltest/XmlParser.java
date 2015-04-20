package com.example.xmltest;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.text.TextUtils;
/**
 * write by weipingshun
 * 20150416
 * parse xml file
 * */
public class XmlParser {
	//private Element mRoot;
	private String mName;
	private Map<String,String> mMap = new HashMap<String, String>();
	
	private XmlParser() {};
	
	public XmlParser(String name) {
		this.mName = name;
	} 
	
	public void parse(String prefix, Node node, Map<String,String> map) {
		//parse cur node's child node
		if (node.hasChildNodes()) {
			NodeList nodes = node.getChildNodes();
			int len = nodes.getLength();
			Node childrenNode = null;
			String name = null;
			String value = null;
			String childrenPrefix = null;
			for (int i=0; i<len; i++) {
				childrenNode = nodes.item(i);
				value = childrenNode.getNodeValue();
				value = null == value ? value : value.replaceAll("[\r|\n|\t| ]", "");
				if (Node.TEXT_NODE == childrenNode.getNodeType() && 
					(null != value && !TextUtils.equals("", value))	) {
					map.put(prefix, value);
				} else if (Node.ELEMENT_NODE == childrenNode.getNodeType()) {
					name = childrenNode.getNodeName();
					childrenPrefix = prefix == null ? name : prefix + ":" + name;
					parse(childrenPrefix, childrenNode, map);
				}
			}
		}
		
		/*  <common id="1000">  here id is a attributes
		//parse cur node attributes
		if (node.hasAttributes()) {
			NamedNodeMap attributes = node.getAttributes();
			int len = attributes.getLength();
			Node childrenNode = null;
			String name = null;
			String value = null;
			String childrenPrefix = null;
			for (int i=0; i<len; i++) {
				childrenNode = attributes.item(i);
				name = childrenNode.getNodeValue();
				value = childrenNode.getNodeValue();
				childrenPrefix = prefix == null ? name : prefix + ":" + name;
				map.put(childrenPrefix, value);
			}
		}*/
		return;
	}
	
	/*public void setmName(String name) {
		mName = name;
	}*/
	
	
	public boolean load() {
		mMap.clear();
		InputStream in = null;
		try {  
			in = MainActivity.class.getResourceAsStream(mName);
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(in); 
			doc.normalize();
			//Node root = doc.getDocumentElement();
			//Node rootNode2 = 
			parse(null, doc, mMap);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getString(String s) {
		if (mMap.containsKey(s)) {
			return mMap.get(s);
		}
		return null;
	}
	
	//public int getInteger(String s) {
	//	String
	//}
}

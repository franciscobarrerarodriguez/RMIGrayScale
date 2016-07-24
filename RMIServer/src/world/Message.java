package world;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ImageIcon imageIcon;
	private String name;

	public Message(ImageIcon imageIcon, String name) {
		this.imageIcon = imageIcon;
		this.name = name;
	}

	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

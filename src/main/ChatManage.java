package main;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class ChatManage {

	private List<ChatSocket> list = new Vector<ChatSocket>();

	private static final ChatManage cm = new ChatManage();

	private ChatManage() {
	}

	public static ChatManage getCM() {
		return cm;
	}

	public void add(ChatSocket cs) {
		list.add(cs);
	}

	public void remove(ChatSocket cs) {
		Iterator<ChatSocket> ite = list.iterator();
		while (ite.hasNext()) {
			if (ite.next() == cs) {
				ite.remove();
				break;
			}
		}
	}

	public void publish(ChatSocket cs, String out) {
		for (int i = 0; i < list.size(); i++) {
			ChatSocket s = list.get(i);
			if (!s.equals(cs)) {
				s.out(out + "\n");
			}
		}
	}

}

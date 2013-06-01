import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;


public class Parser {
	static public List<IssueData> ParserMethod() throws Exception{
		WebRequest wr = new WebRequest();
		wr.getMethod("http://apis.daum.net/socialpick/search", "");
		List<IssueData>  list=find(wr.getResultStream());
		wr.close();
		return list;
	}
	static public List<IssueData> find(InputStream is) throws Exception {
		SAXBuilder builder = new SAXBuilder();
		List<IssueData> list = new ArrayList<IssueData>(1);
		Document doc = builder.build(is);
		Element root = doc.getRootElement();
		List<Element> els = root.getChildren("item");
		if (els.size() > 1) {
			for (int i = 0; i < els.size(); i++) {
				WebRequest wr = new WebRequest();
				wr.getMethod("http://apis.daum.net/search/image", "apikey=DAUM_SEARCH_DEMO_APIKEY&q="
						+URLEncoder.encode(els.get(i).getChild("keyword").getValue())
						+"&result=5&pageno=1"
						);
				list.add(new IssueData(els.get(i).getChild("keyword")
						.getValue(), els.get(i).getChild("content").getValue(),
						els.get(i).getChild("link").getValue(),
						findImage(wr.getResultStream())
						));
				wr.close();
			}
		}
		return list;
	}

	static public String findImage(InputStream is) throws Exception {
		SAXBuilder builder = new SAXBuilder();
		StringBuilder result = new StringBuilder();
		Document doc = builder.build(is);
		Element root = doc.getRootElement();
		List<Element> els = root.getChildren("item");
		if (els.size() > 0) {
			for (int i = 0; i < els.size(); i++) {
				if (i < els.size() - 1) {
					result.append(els.get(i).getChild("thumbnail").getValue()
							+ ";");
				} else {
					result.append(els.get(i).getChild("thumbnail").getValue());
				}
			}
		}
		return result.toString();
	}
}

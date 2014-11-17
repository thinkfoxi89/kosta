package control;

import action.Action;
import action.IndexModel;
import action.ValueModel;

//Servlet�� ���������� �����ϴ� Ŭ���� ******
//(�����尡 �����Ѵ�.)

public class ActionFactory {
	private static ActionFactory factory;
	
	public static synchronized ActionFactory getFactory() {
		if(factory == null) factory = new ActionFactory();
		return factory;
	}
	//��Ʈ�ѷ��� �𵨻��̿��� �߰� ���� ������ �ϴ� ��ü - ��Ʈ�ѷ����Լ����� ��û�� �޾Ƽ�
	//�ش� ���� ������ �ּҰ��� �ٽ� ��Ʈ�ѷ����� �����ؼ� ��Ʈ�ѷ��� �ֹ��� ��(ActionForward)��
	//���� �޵��� �����ϴ� ������ �Ѵ�.
	//--������ / ��ü���� ���յ��� ���߱� ���� ��ü�� ����� ���� ObjectFactory�� �ֱ���
	//��û�� ó���ϰ� �ڵ鸵�ϴ� ���䵵 �߿�
	public Action makeAction(String query){
		Action action = null;
		if(query.equals("index")){
			action = new IndexModel();
		}else if(query.equals("v1")){
			action = new ValueModel();
		}else if(query.equals("date")){
			action =new ValueModel();
		}
		return action;
	}
	
}
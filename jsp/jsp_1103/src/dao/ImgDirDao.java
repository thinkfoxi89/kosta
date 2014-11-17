package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.ConnUtil;
import vo.ImgDirVO;

public class ImgDirDao {
	// ���� ImgDirDao ��ü�� �������̳� JSP���� Model1������� ����Ǿ���.
	// Thread�� ������ �ڿ��̱� ������ ����ȭ ó���� �Ǿ�� �ϰ�
	// �ڿ��� ������ Connection�� �޾Ƽ� ����ؾ� �ϱ� ������
	// �̱��� ������ ������ ����ؼ� ó���� ������ static ������ �ϳ��� �������ѳ���
	// �� �Ŀ��� �̹� ������ �ּҷ� ����ϵ��� �Ѵ�.
	// �ȵ���̵忡�� GridView�� �� �̹� �������� �̱����� ������ �־��.
	private static ImgDirDao dao;

	// ����ȭ ó��
	public synchronized static ImgDirDao getDao() {
		if (dao == null) {
			dao = new ImgDirDao();
		}
		return dao;
	}

	// ���뼺�� �԰��� SQL���� �����ϴ� �޼��带 ����� ����.
	//add
	public void addImg(ImgDirVO vo) {
		Connection con = null;
		PreparedStatement pstm = null;
		StringBuffer sql = new StringBuffer();
		sql.append("insert into imgdir values(imgdir_seq.nextVal,");
		sql.append("?,?,0,?,sysdate)");
		try {
			con = ConnUtil.getDs();
			pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, vo.getWriter());
			pstm.setString(2, vo.getImgpath());
			pstm.setString(3, vo.getReip());
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	//list
	public ArrayList<ImgDirVO> getList(){
		Connection con= null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		//������ ������VO�� �ּҸ� ����ų �÷��� ����
		ArrayList<ImgDirVO> ar = new ArrayList<ImgDirVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select num, writer, imgpath, hit, edate from imgdir order by 1 desc");
		
		try {
			con = ConnUtil.getDs();
			pstm = con.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while(rs.next()){
				//�ϳ��� row�� ������ VO ��ü
				ImgDirVO v = new ImgDirVO();
				v.setNum(rs.getInt("num"));
				v.setWriter(rs.getString("writer"));
				v.setImgpath(rs.getString("imgpath"));
				v.setHit(rs.getInt("hit"));
				v.setEdate(rs.getString("edate"));
				//ArrayList�� ������ �����͸� ������ VO�� �ּҸ� ����Ų��.
				ar.add(v);
			}
			rs.close();
			pstm.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return ar;//�� �ּҸ� ��ȯ�Ѵ�.
	}
	
}
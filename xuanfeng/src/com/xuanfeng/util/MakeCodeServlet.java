package com.xuanfeng.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Servlet implementation class CheckCodeServlet
 */
@WebServlet("/MakeCodeServlet")
public class MakeCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// ���ڴ��д���ͼ��
		int width = 140, height = 40;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// ��ȡͼ��������
		Graphics g = image.getGraphics();
		// ���������
		Random random = new Random();
		// �趨����ɫ
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// �趨����
		g.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		// �������155�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// ȡ�����������֤��(4λ����)
		String source[] = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
				"v", "w", "x", "y", "z", "0", "2", "3", "4", "5", "6", "7", "8", "9" };
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = source[random.nextInt(61)];
			sRand += rand;
			// ����֤����ʾ��ͼ����
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			// ���ú�����������ɫ��ͬ����������Ϊ����̫�ӽ�������ֻ��ֱ������
			g.drawString(rand, 28 * i + 13, 30);
		}
		// ����֤�����SESSION
		session.setAttribute("post_validate_code", sRand);
		session.setMaxInactiveInterval(120);
		
		System.out.println("post_validate_code11:"+session.getAttribute("post_validate_code"));
//		System.out.println("sessionid-1:"+session.getId());
		// ͼ����Ч
		g.dispose();
		// ���ͼ��ҳ��
		// ImageIO.write(image, "JPEG", res.getOutputStream());
		JPEGImageEncoder encoder;
		try {
			encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
			encoder.encode(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	// ������Χ��������ɫ
		private Color getRandColor(int fc, int bc) {
			Random random = new Random();
			if (fc > 255)
				fc = 255;
			if (bc > 255)
				bc = 255;
			int r = fc + random.nextInt(bc - fc);
			int g = fc + random.nextInt(bc - fc);
			int b = fc + random.nextInt(bc - fc);
			return new Color(r, g, b);
		}
}

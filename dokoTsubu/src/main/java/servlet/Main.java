package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;

import dto.User;
import model.ErrorMsg;
import model.Mutter;
import model.PostMutterLogic;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//つぶやきリストをアプリケーションスコープから取得
		ServletContext application = this.getServletContext();
		List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
		//取得できなかった場合、空のリストをアプリケーションスコープに保存
		if (CollectionUtils.isEmpty(mutterList)) {
			mutterList = new ArrayList<>();
			application.setAttribute("mutterList", mutterList);
		}

		//ログイン情報確認
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			//リダイレクト
			response.sendRedirect("jsp/index.jsp");
		} else {
			//フォワード
			RequestDispatcher dispathcer = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			dispathcer.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//ユーザー情報取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");

		//リクエストパラメータ取得
		String text = request.getParameter("text");

		if (text != null && !text.isEmpty()) {
			//つぶやきリスト取得
			ServletContext application = this.getServletContext();
			List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");

			//つぶやきを作成
			Mutter mutter = new Mutter(user.getName(), text);
			//つぶやきリストに追加
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter, mutterList);
			//つぶやきリストをアプリケーションスコープに保存
			application.setAttribute("mutterList", mutterList);
		} else {

			ErrorMsg errMsg = new ErrorMsg();
			errMsg.setErrMsg("つぶやきが入力されていません");
			request.setAttribute("errMsg", errMsg);

		}
		//フォワード
		RequestDispatcher dispathcer = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispathcer.forward(request, response);

	}

}

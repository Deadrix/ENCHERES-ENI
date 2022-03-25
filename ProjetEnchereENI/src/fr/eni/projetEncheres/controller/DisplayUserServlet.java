
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetEncheres.model.bll.UserManager;
import fr.eni.projetEncheres.model.bo.User;


/**
 * Servlet implementation class AfficherProfil
 */
@WebServlet(name="DisplayUserServlet", urlPatterns = {"/AfficherProfil"})
public class DisplayUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur = new Utilisateur();
	private static UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAfficherProfil() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//On récupère la session
		HttpSession session = request.getSession();

		//Si l'utilisateur n'est pas connecté, on le renvoit sur une page 404
    	if(session.getAttribute("idUtilisateur")!=null) {

    		//On vérifie si l'utilisateur existe
    		if(verifierExistenceUtilisateur(request.getParameter("id"))==false) {

    			//Si non, on redirige vers une page indiquant que l'utilisateur est inconnu
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateurInconnu.jsp").forward(request, response);
			}else {
				try {

	    			//On envoie l'utilisateur recherché à la jsp
					request.setAttribute("utilisateur", utilisateurManager.find(Integer.valueOf(request.getParameter("id"))));

					//Si l'utilisateur connecté est le même que l'utilisateur recherché, on l'autorise à modifier
					if(request.getParameter("id").equals((session.getAttribute("idUtilisateur")))) {
						request.setAttribute("modifier", "oui");
					}

					//On redirige vers la jsp
					this.getServletContext().getRequestDispatcher("/WEB-INF/afficherProfil.jsp").forward(request, response);
				} catch (NumberFormatException e) {

					//On redirige vers une page indiquant que l'utilisateur est inconnu
					this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateurInconnu.jsp").forward(request, response);
				} catch (BuisnessException e) {
					e.printStackTrace();
				}
			}
    	}else {

    		//On redirige vers une page indiquant que l'utilisateur est inconnu
    		this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateurInconnu.jsp").forward(request, response);
    	}
	}

	//Méthode vérifier Existence Utilisateur
		private boolean verifierExistenceUtilisateur(String id) {
			boolean existe=true;
			try {
				utilisateur = utilisateurManager.find(Integer.valueOf(id));
				if(utilisateur.getPseudo()==null) {
					existe=false;
				}
			} catch (NumberFormatException e) {
				existe=false;
			} catch (BuisnessException e) {
				e.printStackTrace();
				existe=false;
			}
			return existe;
		};
}
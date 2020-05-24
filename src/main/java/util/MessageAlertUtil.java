package util;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class MessageAlertUtil {

    public static void SuccessStoreMessage(RedirectAttributes redir) {
        redir.addFlashAttribute("msg", 1);
        redir.addFlashAttribute("type", "success");
        redir.addFlashAttribute("cont", "¡Registro guardado con éxito!");
    }

    public static void SuccessUpdateMessage(RedirectAttributes redir) {
        redir.addFlashAttribute("msg", 1);
        redir.addFlashAttribute("type", "success");
        redir.addFlashAttribute("cont", "¡Registro actualizado con éxito!");
    }

    public static void SuccessBlockUserMessage(RedirectAttributes redir) {
        redir.addFlashAttribute("msg", 1);
        redir.addFlashAttribute("type", "success");
        redir.addFlashAttribute("cont", "¡Usuario bloquedo con éxito!");
    }

    public static void SuccessDeleteMessage(RedirectAttributes redir) {
        redir.addFlashAttribute("msg", 1);
        redir.addFlashAttribute("type", "success");
        redir.addFlashAttribute("cont", "¡Registro eliminado con éxito!");
    }

    public static void UnauthorizedAccessMessage(RedirectAttributes redir) {
        redir.addFlashAttribute("error", 1);
        redir.addFlashAttribute("type", "success");
        redir.addFlashAttribute("msg", "¡Debe Iniciar Sesión!");
    }

    public static void alreadyExistMessage(RedirectAttributes redir, String attr) {
        redir.addFlashAttribute("msg", 1);
        redir.addFlashAttribute("type", "danger");
        redir.addFlashAttribute("cont",
                "Ya existe un registro con " + attr + " especificado");
    }

    public static void alreadyExistCustomMsg(RedirectAttributes redir, String attr) {
        redir.addFlashAttribute("msg", 1);
        redir.addFlashAttribute("type", "danger");
        redir.addFlashAttribute("cont",
                attr);
    }

    public static void restorePasswordMessage(RedirectAttributes redir) {
        redir.addFlashAttribute("msg", 1);
        redir.addFlashAttribute("type", "info");
        redir.addFlashAttribute("cont","Por favor verifique su bandeja de entrada en su correo");
    }
}

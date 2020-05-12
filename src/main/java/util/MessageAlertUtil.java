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

    public static void UnauthorizedAccessMessage(RedirectAttributes redir) {
        redir.addFlashAttribute("error", 1);
        redir.addFlashAttribute("type", "success");
        redir.addFlashAttribute("msg", "¡Debe Iniciar Sesión!");
    }
}

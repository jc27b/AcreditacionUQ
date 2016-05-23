package co.edu.uniquindio.android.electiva.acreditacionuq.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import co.edu.uniquindio.android.electiva.acreditacionuq.fragment.QuestionFragment;
import co.edu.uniquindio.android.electiva.acreditacionuq.vo.Question;

/**
 * Adaptador de tipo FragmentPager, el cual utilizará fragmentos
 * para mostrar cada una de las páginas de un view pager. Se encarga
 * de gestionar la lista de páginas, conociendo el tamaño de ésta,
 * configurando el fragmento que deberá ir en cada posición y
 * también el título de cada página.
 */
public class AdaptadorDePagerFragment extends FragmentPagerAdapter {

    private ArrayList<String> listaDeTiulos;
    private ArrayList<Question> preguntas;

    /**
     * Constructor público del adaptador que asigna
     * el manejador de fragmentos que utilizará, y
     * crea una lista de títulos para las páginas
     * que gestionará.
     * @param fm Manejador de fragmentos que utilizará el adaptador para asignar fragmentos como páginas.
     */
    public AdaptadorDePagerFragment(FragmentManager fm, ArrayList<Question> preguntas) {
        super(fm);
        listaDeTiulos = new ArrayList<>();
        for (int i = 1; i <= preguntas.size(); i++) {
            listaDeTiulos.add("Pregunta "+i);
        }
        this.preguntas = preguntas;
    }

    /**
     * Metodo que retorna un fragmento con los datos configurados
     * (color y posición) que servirá como página del view pager
     * según la posición que se manda como parámetro. El fragmento
     * es configurado por el método newInstance, pero además
     * se muestra en el logcat un mensaje con la posición.
     * @param position Posición en la lista de páginas.
     * @return Fragmento para visualizar una página.
     */
    @Override
    public Fragment getItem(int position) {
        QuestionFragment f = QuestionFragment.newInstance(preguntas.get(position));

        return f;
    }

    /**
     * Metodo que retorna el número de
     * elementos que maneja el adaptador,
     * el cual es el mismo que el numero
     * de elementos de la lista de titulos.
     * @return Número de elementos del adaptador
     */
    @Override
    public int getCount() {
        return listaDeTiulos.size();
    }

    /**
     * Metodo que retorna el titulo que deberá
     * utilizar cada pagina que muestre el adaptador,
     * segun la posición pasada por parámetro.
     * @param position Posición de la página.
     * @return Título para la página.
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return listaDeTiulos.get(position);
    }

}
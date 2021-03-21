package br.ce.wcaquino.matchers;

import br.ce.wcaquino.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

import static br.ce.wcaquino.utils.DataUtils.*;

public class DiaRetornoComDiferencaMatcher extends TypeSafeMatcher<Date> {

    private int diferencaDias;

    public DiaRetornoComDiferencaMatcher(int diferencaDias) {
        this.diferencaDias = diferencaDias;
    }

    @Override
    protected boolean matchesSafely(Date diaRetorno) {
        return DataUtils.isMesmaData(diaRetorno, obterDataComDiferencaDias(diferencaDias));
    }

    @Override
    public void describeTo(Description description) {

    }
}

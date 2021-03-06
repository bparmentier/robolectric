package org.robolectric.shadows;

import static com.google.common.truth.Truth.assertThat;
import static org.robolectric.Shadows.shadowOf;

import android.app.Application;
import android.content.Context;
import android.view.accessibility.CaptioningManager;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

/** Tests for the ShadowCaptioningManager. */
@RunWith(AndroidJUnit4.class)
@Config(minSdk = 19)
public final class ShadowCaptioningManagerTest {
  private CaptioningManager captioningManager;
  private ShadowCaptioningManager shadowCaptioningManager;

  @Before
  public void setUp() {
    captioningManager =
        (CaptioningManager)
            ((Application) ApplicationProvider.getApplicationContext())
                .getSystemService(Context.CAPTIONING_SERVICE);
    shadowCaptioningManager = shadowOf(captioningManager);
  }

  @Test
  public void setEnabled_true() {
    assertThat(captioningManager.isEnabled()).isFalse();

    shadowCaptioningManager.setEnabled(true);

    assertThat(captioningManager.isEnabled()).isTrue();
  }

  @Test
  public void setEnabled_false() {
    shadowCaptioningManager.setEnabled(false);

    assertThat(captioningManager.isEnabled()).isFalse();
  }

  @Test
  public void setFontScale() {
    float fontScale = 1.5f;
    shadowCaptioningManager.setFontScale(fontScale);

    assertThat(captioningManager.getFontScale()).isWithin(0.001f).of(fontScale);
  }
}

using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;


public class Splash : MonoBehaviour {
	public Image splashImage;
	private LevelManager levelManager;

	IEnumerator Start() {
		levelManager = GameObject.FindObjectOfType<LevelManager>();
		splashImage.canvasRenderer.SetAlpha (0.0f);
		FadeIn ();
		yield return new WaitForSeconds (2.5f);
		FadeOut ();
		yield return new WaitForSeconds (2.5f);
		levelManager.NextLevel();
	}

	void FadeIn() {
		splashImage.CrossFadeAlpha (1.0f, 4.5f, false);
	}
	void FadeOut() {
		splashImage.CrossFadeAlpha (0.0f, 2.5f, false);
	}



}

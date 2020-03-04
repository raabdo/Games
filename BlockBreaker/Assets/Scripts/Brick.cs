using UnityEngine;
using System.Collections;

public class Brick : MonoBehaviour {

	// Use this for initialization
	private int timesHit;
	
	public AudioClip Crack;
	public Sprite[] hitSprites;
	private int maxHits;
	private Behaviour LevelManager;
	public static int NumberOfBrick =0;
	public bool isBreakable;
	void Start () {
		LevelManager = GameObject.FindObjectOfType<Behaviour>();
		timesHit =0; 
		maxHits = hitSprites.Length+1;
		isBreakable = (this.tag == "Breakable");
		
		if (isBreakable) {
			NumberOfBrick++;
			
		}
	}
	
	void OnCollisionEnter2D (Collision2D collision) {
		AudioSource.PlayClipAtPoint(Crack, transform.position);
		if (isBreakable) HitsHandle();
		
	}
	
	void HitsHandle(){
		timesHit++;
				
		if (timesHit >= maxHits) {
			NumberOfBrick--;
			LevelManager.BrickDestroyed();
			Destroy(gameObject);
		}
		
		else LoadSprites() ;
		
	}
	
	void LoadSprites() {
		 int indexSprites = timesHit-1;
		 if (hitSprites[indexSprites])
		 this.GetComponent<SpriteRenderer>().sprite = hitSprites[indexSprites];
	}
	
	
	// Update is called once per frame
	void Update () {
		
		
	}
}

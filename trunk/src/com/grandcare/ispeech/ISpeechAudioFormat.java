package com.grandcare.ispeech;

public enum ISpeechAudioFormat {
	AIFF("aiff", "Audio Interchange File Format (16khz, 16bit, mono)"),
	FLAC("flac", "Free Loseless Audio Codec (16khz, 16bit, mono)"),
	MP3 ("mp3", "MPEG-1 Audio Layer 3 (48kBPS, 16kHz, Mono)"),
	MP4 ("mp4", "MPEG-4 Part 14 (33kBPS, 44kHz, Stereo)"),
	OGG ("ogg", "OGG File Format (48kBPS, 22kHz, Mono)"),
	VOX ("vox", "VOX File Format (128kBPS, 8kHz, Mono)"),
	WAV ("wav", "Waveform Audio Format (16kHz, 16bit, Mono)"),
	WMA ("wma", "Windows Media Audio (48kBPS, 44kHz, Mono)");
	
	private String code;
	private String description;
	
	private ISpeechAudioFormat(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public String toParamString() {
		return code;
	}

	public String getDescription() {
		return description;
	}
}

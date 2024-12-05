FluidBufVoiceAllocator : FluidBufProcessor {
	*kr  { |freqIn, startFrameA = 0, numFramesA = -1, startChanA = 0, numChansA = -1, magIn, startFrameB = 0, numFramesB = -1, startChanB = 0, numChansB = -1, freqOut, magOut, voiceState, numVoices = 1, prioritisedVoices = 0, birthLowThreshold = -24, birthHighThreshold = -60, minTrackLen = 1, trackMagRange = 15, trackFreqRange = 50, trackProb = 0.5, trig = 1, blocking = 0|

		freqIn.isNil.if {"FluidBufVoiceAllocator:  Invalid source 1 buffer".throw};
		magIn.isNil.if {"FluidBufVoiceAllocator:  Invalid source 2 buffer".throw};
		freqIn = freqIn.asUGenInput;
		magIn = magIn.asUGenInput;

		freqOut.isNil.if {"FluidBufVoiceAllocator:  Invalid destination buffer".throw};
		magOut.isNil.if {"FluidBufVoiceAllocator:  Invalid destination buffer".throw};
		voiceState.isNil.if {"FluidBufVoiceAllocator:  Invalid destination buffer".throw};
		freqOut = freqOut.asUGenInput;
		magOut = magOut.asUGenInput;
		voiceState = voiceState.asUGenInput;

		^FluidProxyUgen.kr(this.objectClassName++\Trigger, -1, freqIn, startFrameA, numFramesA, startChanA, numChansA, magIn, startFrameB, numFramesB, startChanB, numChansB, freqOut, magOut, voiceState, numVoices, numVoices, prioritisedVoices, birthLowThreshold, birthHighThreshold, minTrackLen, trackMagRange, trackFreqRange, trackProb, trig, blocking);
	}

	*process { |server, freqIn, startFrameA = 0, numFramesA = -1, startChanA = 0, numChansA = -1, magIn, startFrameB = 0, numFramesB = -1, startChanB = 0, numChansB = -1, freqOut, magOut, voiceState, numVoices = 1, prioritisedVoices = 0, birthLowThreshold = -24, birthHighThreshold = -60, minTrackLen = 1, trackMagRange = 15, trackFreqRange = 50, trackProb = 0.5, freeWhenDone = true, action|

		freqIn.isNil.if {"FluidBufVoiceAllocator:  Invalid source 1 buffer".throw};
		magIn.isNil.if {"FluidBufVoiceAllocator:  Invalid source 2 buffer".throw};
		freqIn = freqIn.asUGenInput;
		magIn = magIn.asUGenInput;

		freqOut.isNil.if {"FluidBufVoiceAllocator:  Invalid destination buffer".throw};
		magOut.isNil.if {"FluidBufVoiceAllocator:  Invalid destination buffer".throw};
		voiceState.isNil.if {"FluidBufVoiceAllocator:  Invalid destination buffer".throw};
		freqOut = freqOut.asUGenInput;
		magOut = magOut.asUGenInput;
		voiceState = voiceState.asUGenInput;

		^this.new(
			server, nil, [freqOut, magOut, voiceState]
		).processList(
			[freqIn, startFrameA, numFramesA, startChanA, numChansA, magIn, startFrameB, numFramesB, startChanB, numChansB, freqOut, magOut, voiceState, numVoices, numVoices, prioritisedVoices, birthLowThreshold, birthHighThreshold, minTrackLen, trackMagRange, trackFreqRange, trackProb, 0], freeWhenDone, action
		)
	}

	*processBlocking { |server, freqIn, startFrameA = 0, numFramesA = -1, startChanA = 0, numChansA = -1, magIn, startFrameB = 0, numFramesB = -1, startChanB = 0, numChansB = -1, freqOut, magOut, voiceState, numVoices = 1, prioritisedVoices = 0, birthLowThreshold = -24, birthHighThreshold = -60, minTrackLen = 1, trackMagRange = 15, trackFreqRange = 50, trackProb = 0.5, freeWhenDone = true, action|

		freqIn.isNil.if {"FluidBufVoiceAllocator:  Invalid source 1 buffer".throw};
		magIn.isNil.if {"FluidBufVoiceAllocator:  Invalid source 2 buffer".throw};
		freqIn = freqIn.asUGenInput;
		magIn = magIn.asUGenInput;

		freqOut.isNil.if {"FluidBufVoiceAllocator:  Invalid destination buffer".throw};
		magOut.isNil.if {"FluidBufVoiceAllocator:  Invalid destination buffer".throw};
		voiceState.isNil.if {"FluidBufVoiceAllocator:  Invalid destination buffer".throw};
		freqOut = freqOut.asUGenInput;
		magOut = magOut.asUGenInput;
		voiceState = voiceState.asUGenInput;

		^this.new(
			server, nil, [freqOut, magOut, voiceState]
		).processList(
			[freqIn, startFrameA, numFramesA, startChanA, numChansA, magIn, startFrameB, numFramesB, startChanB, numChansB, freqOut, magOut, voiceState, numVoices, numVoices, prioritisedVoices, birthLowThreshold, birthHighThreshold, minTrackLen, trackMagRange, trackFreqRange, trackProb, 1], freeWhenDone, action
		)
	}
}

FluidBufVoiceAllocatorTrigger : FluidProxyUgen {}

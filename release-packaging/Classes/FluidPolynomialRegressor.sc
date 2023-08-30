FluidPolynomialRegressor : FluidModelObject {

	var <>degree, <>tikhonov;

	*new {|server, degree = 2, tikhonov = 0|
		^super.new(server,[degree,tikhonov])
		.degree_(degree)
		.tikhonov_(tikhonov);
	}

	prGetParams{^[this.id,this.degree,this.tikhonov,-1,-1];}

	fitMsg{|sourceDataSet, targetDataSet|
		^this.prMakeMsg(\fit,this.id,sourceDataSet.id,targetDataSet.id)
	}

	fit{|sourceDataSet, targetDataSet, action|
		actions[\fit] = [nil,action];
		this.prSendMsg(this.fitMsg(sourceDataSet, targetDataSet));
	}

	predictMsg{ |sourceDataSet, targetDataSet|
		^this.prMakeMsg(\predict,this.id,sourceDataSet.id,targetDataSet.id)
	}

	predict{ |sourceDataSet, targetDataSet, action|
		actions[\predict] = [nil, action];
		this.prSendMsg(this.predictMsg(sourceDataSet, targetDataSet));
	}

	predictPointMsg { |sourceBuffer, targetBuffer|
		^this.prMakeMsg(\predictPoint,id,
			this.prEncodeBuffer(sourceBuffer),
			this.prEncodeBuffer(targetBuffer),
			["/b_query", targetBuffer.asUGenInput]);
	}

	predictPoint { |sourceBuffer, targetBuffer, action|
		actions[\predictPoint] = [nil,{action.value(targetBuffer)}];
		this.predictPointMsg(sourceBuffer, targetBuffer);
		this.prSendMsg(this.predictPointMsg(sourceBuffer, targetBuffer));
	}

	kr{|trig, inputBuffer, outputBuffer, numNeighbours, weight|
		^FluidPolynomialRegressorQuery.kr(K2A.ar(trig),
			this, numNeighbours??{this.numNeighbours}, weight??{this.weight},
			this.prEncodeBuffer(inputBuffer),
			this.prEncodeBuffer(outputBuffer));
	}
}

FluidPolynomialRegressorQuery : FluidRTMultiOutUGen {

	*kr{ |trig, model, degree = 2, tikhonov = 0, inputBuffer, outputBuffer |
		^this.multiNew('control',trig, model.asUGenInput,
			degree, tikhonov,
			inputBuffer.asUGenInput, outputBuffer.asUGenInput)
	}

	init { arg ... theInputs;
		inputs = theInputs;
		^this.initOutputs(1, rate);
	}
}
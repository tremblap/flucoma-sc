FluidRobustScale : FluidRealTimeModel {

    var <>min, <>max, <>low, <>high, <>invert;

	*new {|server, min = 0, max = 1, low = 0, high = 100, invert = 0|
		^super.new(server,[min,max,low,high,invert])
		.min_(min).max_(max).low_(low).high_(high).invert_(invert);
	}

    prGetParams{
        ^[this.min,this.max,this.low,this.high,this.invert,-1,-1];
    }


    fitMsg{|dataSet|
        ^this.prMakeMsg(\fit,id,dataSet.id)
    }

	fit{|dataSet, action|
        actions[\fit] = [nil,action];
		this.prSendMsg(this.fitMsg(dataSet));
	}

    transformMsg{|sourceDataSet, destDataSet|
        ^this.prMakeMsg(\transform,id,sourceDataSet.id,destDataSet.id);
    }

	transform{|sourceDataSet, destDataSet, action|
		actions[\transform] = [nil,action];
        this.prSendMsg(this.transformMsg(sourceDataSet, destDataSet));
	}

    fitTransformMsg{|sourceDataSet, destDataSet|
        ^this.prMakeMsg(\fitTransform,id,sourceDataSet.id,destDataSet.id)
    }

	fitTransform{|sourceDataSet, destDataSet, action|
        actions[\fitTransform] = [nil,action];
		this.prSendMsg(this.fitTransformMsg(sourceDataSet, destDataSet));
	}

    transformPointMsg{|sourceBuffer, destBuffer|
        ^this.prMakeMsg(\transformPoint,id,
            this.prEncodeBuffer(sourceBuffer),
            this.prEncodeBuffer(destBuffer),
            ["/b_query",destBuffer.asUGenInput]
        );
    }

	transformPoint{|sourceBuffer, destBuffer, action|
        actions[\transformPoint] = [nil,{action.value(destBuffer)}];
        this.prSendMsg(this.transformPointMsg(sourceBuffer, destBuffer));
	}

    kr{|trig, inputBuffer,outputBuffer,min,max,low,high,invert|

        min = min ? this.min;
        max = max ? this.max;
        low = low ? this.low;
        high = high ? this.high;
		invert = invert ? this.invert;

        this.min_(min).max_(max).low_(low).high_(high).invert_(invert);

        ^FluidProxyUgen.kr(this.class.name.asString++'/query', K2A.ar(trig),
                id, this.min, this.max, this.low, this.high, this.invert, this.prEncodeBuffer(inputBuffer), this.prEncodeBuffer(outputBuffer));
    }


}

#!/usr/bin/env node
import cdk = require('@aws-cdk/core');
import { SampleAppStack } from '../lib/sample-app-stack';

const app = new cdk.App();
new SampleAppStack(app, 'SampleAppStack');
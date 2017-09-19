const path = require('path');
const webpack = require('webpack');
const ZipPlugin = require('zip-webpack-plugin');

// const ExtractTextPlugin = require('extract-text-webpack-plugin');

const HtmlWebpackPlugin = require('html-webpack-plugin');

const stylesheetsLoader =
  'style-loader!css-loader?modules&localIdentName=[path]-[local]-[hash:base64:3]';
const htmlWebpackPlugin = new HtmlWebpackPlugin({ template: 'index.html' });
const definePlugin = new webpack.DefinePlugin({
  __DEV__: JSON.stringify(JSON.parse(process.env.NODE_ENV === 'development' || 'true'))
});

module.exports = {
  context: path.join(__dirname, 'src'),
  entry: './index',
  output: {
    filename: '[hash].js',
    publicPath: '/'
  },
  devtool: 'source-map',
  plugins: [
    htmlWebpackPlugin,
    definePlugin,
    new ZipPlugin({
      path: path.join(__dirname, 'src'),
      filename: 'dist.zip'
    })
  ],
  resolve: {
    modules: ['node_modules', path.join(__dirname, 'src')]
  },
  module: {
    loaders: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        loader: 'babel-loader',
      },
      {
        test: /\.css$/,
        use: [
            { loader: 'style-loader' },
            { loader: 'css-loader', options: { modules: true, importLoaders: 1 } },
            { loader: 'postcss-loader', options: { config: { path: 'path/to/postcss.config.js' } } }
        ],
        exclude: /node_modules/
      },
      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader', 'postcss-loader'],
        include: /node_modules/
      },

      { test: /\.scss$/, loader: `${stylesheetsLoader}'!sass` },
      { test: /\.sass$/, loader: `${stylesheetsLoader}'!sass?indentedSyntax=sass` },
      { test: /\.less$/, loader: `${stylesheetsLoader}'!less` },
      { test: /\.html$/, loader: 'html-loader' },
      { test: /\.(png|jpg|gif|mp4|ogg|svg|woff|woff2)$/i, loader: 'url-loader?limit=20000' }
    ]
  },
  devServer: {
    historyApiFallback: true,
    hot: true,
    port: 8080,
    proxy: {
      '/api': {
        // http://g4yriq.natappfree.cc/api/participator/insert
        // target: 'http://g4yriq.natappfree.cc',
        // pathRewrite: { '^/api': '' }
      }
    }
  }
};

// pathRewrite: { '^/api': '' },
// secure: false

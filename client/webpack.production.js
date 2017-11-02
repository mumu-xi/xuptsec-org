const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const CompressionPlugin = require('compression-webpack-plugin');
const ZipPlugin = require('zip-webpack-plugin');

const stylesheetsLoader = ExtractTextPlugin.extract({
  fallbackLoader: 'style-loader',
  loader: 'css-loader?modules&localIdentName=[hash:base64]'
});
const stylesheetsPlugin = new ExtractTextPlugin('[hash].css');
const htmlWebpackPlugin = new HtmlWebpackPlugin({ template: 'index.html' });
const definePlugin = new webpack.DefinePlugin({
  __DEV__: JSON.stringify(JSON.parse(process.env.NODE_ENV === 'development' || 'false')),
  'process.env': {
    NODE_ENV: JSON.stringify(process.env.NODE_ENV || 'production')
  }

});
const uglifyPlugin = new webpack.optimize.UglifyJsPlugin({ compress: { warnings: false } });
const AggressiveMergingPlugin = new webpack.optimize.AggressiveMergingPlugin();
const compressionPlugin = new CompressionPlugin();

module.exports = {
  context: path.join(__dirname, 'src'),
  entry: './index',
  output: {
    publicPath: '/',
    filename: '[hash].js',
    path: path.join(__dirname, 'dist')
  },
  devtool: 'cheap-source-map',
  plugins: [
    stylesheetsPlugin,
    htmlWebpackPlugin,
    definePlugin,
    uglifyPlugin,
    compressionPlugin,
    AggressiveMergingPlugin,
    new ZipPlugin({
      path: path.join(__dirname, 'dist'),
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
        query: {
          plugins: [
           ['import', { libraryName: 'antd', style: 'css' }] // `style: true` 会加载 less 文件
          ]
        }
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
        use: [
            { loader: 'style-loader' },
            { loader: 'css-loader' },
            { loader: 'postcss-loader' }
        ],
        include: /node_modules/
      },
      { test: /\.scss$/, loader: `${stylesheetsLoader}'!sass` },
      { test: /\.sass$/, loader: `${stylesheetsLoader}'!sass?indentedSyntax=sass` },
      { test: /\.less$/, loader: `${stylesheetsLoader}'!less` },
      { test: /\.html$/, loader: 'html-loader' },
      { test: /\.(png|jpg|gif|mp4|ogg|svg|woff|woff2)$/i,
        use: [
            { loader: 'url-loader', options: { limit: 10000 } }
        ]
      }
    ]
  }
};
